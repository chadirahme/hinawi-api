package com.hinawi.api;

import com.hinawi.api.controller.ListController;
import com.hinawi.api.domains.HRListFields;
import com.hinawi.api.domains.HRListValues;
import com.hinawi.api.repository.HRListValuesRepository;
import com.hinawi.api.services.ListService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


//@RunWith(SpringRunner.class)
//@DataJpaTest
public class ListRepositoryIntegrationTest {

    //https://www.baeldung.com/spring-boot-testing

   // @Autowired
   // private TestEntityManager entityManager;

    //@Autowired
    //private HRListValuesRepository hrListValuesRepository;

    @InjectMocks
    private ListController listController;

    @Mock
    private HRListValuesRepository hrListValuesRepository;

    @Mock
    private ListService listService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getMaxId() {
        // given
        //ListController listController=new ListController();
        //HRListValues u = new HRListValues();
        //u.setId(1l);
        List<HRListFields> lst=new ArrayList<>();
        HRListFields hrListFields=new HRListFields();
        hrListFields.setFieldId(1l);
        lst.add(hrListFields);
        hrListFields=new HRListFields();
        hrListFields.setFieldId(2l);
        lst.add(hrListFields);
        when(hrListValuesRepository.getMaxId()).thenReturn(2L);
        when(listService.getHRListFields()).thenReturn(lst);


        // when
        long found = listController.getMax(1L);

        //check if a certain method of a mock object has been called by specific number of times.
        verify(listService,times(1)).getHRListFields();
        // then
        assertEquals(found,2L);
    }


}
