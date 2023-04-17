package com.marcapo.exercise.springbootstartup;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.security.MD5Encoder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class SpringbootstartupApplicationTests
{

	public static String asJsonString(final Object obj)
	{
		try
		{
			return new ObjectMapper().writeValueAsString(obj);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	private String encodedPassword;

	private Logger log = LogManager.getLogger(this.getClass());

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MockMvc mvc;

	private String userJson;

	private String username;

	@AfterEach
	@BeforeEach
	void cleanup() throws Exception
	{
		this.mongoTemplate.remove(new Query(), "user");
	}

	@Test
	void createAndLoadUser() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		this.mvc.perform(MockMvcRequestBuilders.get("/users/search/findByUsername?username=" + this.username)).andExpectAll(
			MockMvcResultMatchers.status().is2xxSuccessful());
	}

	@Test
	void createAndLoadUserCheckFileds() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		this.mvc.perform(MockMvcRequestBuilders.get("/users/search/findByUsername?username=" + this.username)).andExpectAll(
			MockMvcResultMatchers.status().is2xxSuccessful(),
			MockMvcResultMatchers.jsonPath("$.username").exists(), MockMvcResultMatchers.jsonPath("$.password").exists());
	}

	@Test
	void createAndLoadUserCheckFiledsAndPasswordEncoding() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		this.mvc.perform(MockMvcRequestBuilders.get("/users/search/findByUsername?username=" + this.username)).andExpectAll(
			MockMvcResultMatchers.status().is2xxSuccessful(),
			MockMvcResultMatchers.jsonPath("$.username").exists(), MockMvcResultMatchers.jsonPath("$.password").value(this.encodedPassword));
	}

	@Test
	void createUser() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	@Test
	void createUserAndDelete() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		this.mvc.perform(MockMvcRequestBuilders.get("/users/search/deleteByUsername?username=" + this.username))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

	}

	private String encodeMD5(final String value)
	{
		try
		{
			if (value != null)
			{
				byte[] bytesOfMessage = value.getBytes(StandardCharsets.UTF_8);
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] thedigest = md.digest(bytesOfMessage);
				return MD5Encoder.encode(thedigest);
			}
		}
		catch (NoSuchAlgorithmException e)
		{
			this.log.error(e.getMessage(), e);
		}
		return null;
	}

	@BeforeEach
	void init()
	{
		this.username = "myfirstUser";
		String password = "savepassword";
		this.encodedPassword = this.encodeMD5(password);
		this.userJson = String.format("{\"username\":\"%s\", \"password\":\"%s\"}", this.username, password);
	}

	@Test
	void uniqueUsernameCheck() throws Exception
	{
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
		this.mvc.perform(MockMvcRequestBuilders.post("/users").content(this.userJson))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError());
		this.mvc.perform(MockMvcRequestBuilders.get("/users/search/findByUsername?username=" + this.username)).andExpectAll(
			MockMvcResultMatchers.status().is2xxSuccessful());
	}
}