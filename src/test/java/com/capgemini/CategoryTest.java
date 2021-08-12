package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.GroupUserVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserStatus;
import com.capgemini.model.UserVO;
import com.capgemini.service.CategoryService;
import com.capgemini.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para CategoryVO")
class CategoryTest {

	@Autowired
	private CategoryService catService;

	@Autowired
	private UserService userService;

	@Test
	@Order(1)
	@DisplayName("Insertar categorias")
	void testInsertCategory() {
		System.out.println();
		System.out.println("[TEST 1]");

		UserVO user = new UserVO("usuario1", "usuario1@capgemini.com", "usuario1", UserStatus.ENABLED, true,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>());
		userService.add(user);// necesario para poder añadir categorias

		catService.add(new CategoryVO("categoria1", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria2", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria3", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria4", new ArrayList<TaskVO>(), user));
		catService.add(new CategoryVO("categoria5", new ArrayList<TaskVO>(), user));

		assertEquals(5, catService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

}
