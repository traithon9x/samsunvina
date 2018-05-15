package com.samsun.samsunvina.services;

import com.samsun.samsunvina.SamsunvinaApplication;
import com.samsun.samsunvina.entities.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SamsunvinaApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
public class AccountServiceImplTest {

    @Autowired
    AccountServiceImpl accountService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void save() throws Exception {
    }

    @Test
    public void whenFindByUserThenReturnNull() {
        String username = "hoanglongckd1";
        Optional<Account> account = accountService.findByUsername(username);
        assertFalse(account.isPresent());
    }

    @Test
    public void whenFindByUsernameThenReturnAccount() {
        String username = "hoanglongckd";
        Optional<Account> account = accountService.findByUsername(username);
        assertTrue(account.isPresent());
        assertEquals(username, account.get().getUsername());
    }

}