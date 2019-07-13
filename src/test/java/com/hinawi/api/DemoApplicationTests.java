package com.hinawi.api;

import com.hinawi.api.repository.CheckMastRepository;
import com.hinawi.api.repository.PaymentsStatistics;
import com.hinawi.api.services.impl.AccountServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	//https://dzone.com/articles/unit-and-integration-tests-in-spring-boot-2

	@Mock
	CheckMastRepository checkMastRepository;
	///private RestTemplate restTemplate;

	@InjectMocks
	private AccountServiceImpl accountService=new AccountServiceImpl();

	@Test
	public void contextLoads() {
	}

	@Test
	public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
		//Employee emp = new Employee(“E001”, "Eric Simmons");
//		Mockito
//				.when(restTemplate.getForEntity("http://localhost:8080/employee/E001", Employee.class))
//          .thenReturn(new ResponseEntity(emp, HttpStatus.OK));

		List<PaymentsStatistics>  lst=accountService.findAllPettyCashByYear(2018);
		assertEquals(lst.size(), 12);
	}

	@Test
	public void testFindTheGreatestFromAllData() {
		//DataService dataServiceMock = mock(DataService.class);
//		Mockito.when(checkMastRepository.findAllPettyCashByYear(2018)).thenReturn(
//				 ret(
//					new PaymentsStatistics() {
//						@Override
//						public Double getTotal() {
//							return null;
//						}
//
//						@Override
//						public int getPaymonth() {
//							return 0;
//						}
//					}));



		//SomeBusinessImpl businessImpl = new SomeBusinessImpl(dataServiceMock);
		//int result = businessImpl.findTheGreatestFromAllData();
		List<PaymentsStatistics>  lst=accountService.findAllPettyCashByYear(2018);
		assertEquals(12, lst.size());
	}


}

