package com.capgemini;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.capgemini.model.CategoryVO;
import com.capgemini.model.GroupUserVO;
import com.capgemini.model.GroupVO;
import com.capgemini.model.TaskVO;
import com.capgemini.model.UserStatus;
import com.capgemini.model.UserVO;
import com.capgemini.service.GroupService;
import com.capgemini.service.GroupUserService;
import com.capgemini.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test para GroupUserVO")
class GroupUserTest {

	@Autowired
	private GroupUserService groupUserService;

	@Autowired
	private UserService userService;

	@Autowired
	private GroupService groupService;

	@Test
	@Order(1)
	@DisplayName("Insertar groupuser")
	void testInsertGroupUser() {
		System.out.println();
		System.out.println("[TEST 1]");

		// necesario crear un usuario y varios grupos
		UserVO user = new UserVO("usuario1", "usuario1@capgemini.com", "usuario1", UserStatus.ENABLED, true,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>());

		// añadimos el user
		userService.add(user);

		GroupVO group1 = new GroupVO("grupo1", "grupo1 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group2 = new GroupVO("grupo2", "grupo2 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group3 = new GroupVO("grupo3", "grupo3 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group4 = new GroupVO("grupo4", "grupo4 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());
		GroupVO group5 = new GroupVO("grupo5", "grupo5 para testing", new Date(), new ArrayList<TaskVO>(),
				new ArrayList<GroupUserVO>());

		// añadimos los grupos
		groupService.add(group1);
		groupService.add(group2);
		groupService.add(group3);
		groupService.add(group4);
		groupService.add(group5);

		groupUserService.add(new GroupUserVO(true, user, group1));
		groupUserService.add(new GroupUserVO(false, user, group2));
		groupUserService.add(new GroupUserVO(false, user, group3));
		groupUserService.add(new GroupUserVO(true, user, group4));
		groupUserService.add(new GroupUserVO(false, user, group5));

		assertEquals(5, groupUserService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(2)
	@DisplayName("Borrar groupusers")
	void testDeletetGroupUser() {
		System.out.println();
		System.out.println("[TEST 2]");

		GroupUserVO groupUser = groupUserService.findById(2);
		groupUserService.delete(groupUser);
		assertEquals(4, groupUserService.listAll().size());

		System.out.println();
		System.out.println("________");

	}

	@Test
	@Order(3)
	@DisplayName("Borrar groupuser pasandole su id")
	void testDeletetGroupUseryById() {
		System.out.println();
		System.out.println("[TEST 3]");

		groupUserService.deleteById(3);
		assertEquals(3, groupUserService.listAll().size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(4)
	@DisplayName("Buscar todos los usergroup")
	void testListAllGroupUsers() {
		System.out.println();
		System.out.println("[TEST 4]");

		List<GroupUserVO> groupUsers = groupUserService.listAll();
		System.out.println("Los groupusers de nuestra BBDD son: ");

		for (GroupUserVO gu : groupUsers) {
			System.out.println("GroupUser id: " + gu.getIdgroupUser() + " con usuario "
					+ userService.findById(gu.getUserGroupUser().getIduser()).getLogin() + " y grupo "
					+ groupService.findById(gu.getGroupGroupUser().getIdgroup()).getName());
		}

		assertEquals(3, groupUsers.size());

		System.out.println();
		System.out.println("________");
	}

	@Test
	@Order(5)
	@DisplayName("Buscar groupuser por id")
	void testFindCategoriesById() {
		System.out.println();
		System.out.println("[TEST 5]");

		System.out.println("El groupuser con id[1] es: " + groupUserService.findById(1).getIdgroupUser());

//		assertEquals("usuario1", groupUserService.findById(1).getUserGroupUser().getLogin());
		assertTrue((groupUserService.findById(1).getClass()).equals(GroupUserVO.class));

		System.out.println();
		System.out.println("________");

	}

	@Test
	@Order(6)
	@DisplayName("Modificar groupuser")
	void testModifyCategory() {
		System.out.println();
		System.out.println("[TEST 6]");

		// le cambiamos el usuario

		GroupUserVO groupUser = groupUserService.findById(1);
		System.out.println("El usuario del groupuser con id[1] es: " + groupUser.getUserGroupUser().getLogin());
		
		//creamos un nuevo usuario
		UserVO userModify= new UserVO("userModify", "userModify@capgemini.com", "userModify", UserStatus.ENABLED, true,
				new ArrayList<TaskVO>(), new ArrayList<CategoryVO>(), new ArrayList<GroupUserVO>());
		
		//lo persistimos en BBDD
		userService.add(userModify);

		//y lo seteamos en el groupUser
		groupUser.setUserGroupUser(userModify);

		groupUserService.modify(groupUser);

		System.out.println("Tras los cambios:");
		System.out.println("El usuario del groupuser con id[1] es: " + groupUser.getUserGroupUser().getLogin());

		assertEquals("userModify", groupUserService.findById(1).getUserGroupUser().getLogin());

		System.out.println();
		System.out.println("________");

	}

}
