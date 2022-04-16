package vacuumlabs.trademark;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import vacuumlabs.trademark.facade.TrademarkFacade;
import vacuumlabs.trademark.model.Trademark;
import vacuumlabs.trademark.service.TrademarkRepository;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.properties")
public class TrademarkTest {

    public static final String TRADEMARK_WORD = "TRADEMARK";

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TrademarkRepository repository;
    @Autowired
    private TrademarkFacade trademarkFacade;

    @Test
    public void controllerNotFoundTest()
            throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/")
                .param("name", ""))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(status, HttpStatus.NOT_FOUND.value());
        assertEquals(content, "");
    }

    @Test
    public void controllerFoundTest()
            throws Exception {
        createTestTradeMark(TRADEMARK_WORD + "1");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/")
                .param("name", TRADEMARK_WORD + "1"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(status, HttpStatus.OK.value());
        assertTrue(content.contains(TRADEMARK_WORD + "1"));
    }

    @Test
    public void controllerMultipleNotFoundTest()
            throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/nearest")
                .param("name", "TEST"))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(status, HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void controllerMultipleTest()
            throws Exception {
        createTestTradeMark(TRADEMARK_WORD + "2");
        createTestTradeMark(TRADEMARK_WORD + "3");

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders
                .get("/nearest")
                .param("name", TRADEMARK_WORD))
                .andReturn();

        int status = mvcResult.getResponse().getStatus();
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(status, HttpStatus.OK.value());
        assertTrue(content.contains(TRADEMARK_WORD + "2"));
        assertTrue(content.contains(TRADEMARK_WORD + "3"));
    }

    @Test
    public void facadeNotFoundTest() throws Exception {
        Trademark trademark = trademarkFacade.getTradeMark("NOT_FOUND");
        assertNull(trademark);
    }

    @Test
    public void facadeFoundTest() throws Exception {
        createTestTradeMark(TRADEMARK_WORD + "4");
        Trademark trademark = trademarkFacade.getTradeMark(TRADEMARK_WORD + "4");
        assertNotNull(trademark);
        assertEquals(trademark.getWordMarkSpecification(), TRADEMARK_WORD + "4");
    }

    @Test
    public void facadeAllNotFoundTest() throws Exception {
        List<Trademark> trademarks = trademarkFacade.getTradeMarks("NOT_FOUND");
        assertTrue(trademarks.isEmpty());
    }

    @Test
    public void facadeAllFoundTest() throws Exception {
        createTestTradeMark(TRADEMARK_WORD + "5");
        createTestTradeMark(TRADEMARK_WORD + "56");
        List<Trademark> trademarks = trademarkFacade.getTradeMarks(TRADEMARK_WORD + "5");
        assertEquals(trademarks.size(), 2);
    }

    @Test
    public void facadeMock() throws Exception {
        Trademark trademark = new Trademark(TRADEMARK_WORD + "7", new Date(), new Date(),
                new Date(), TRADEMARK_WORD + "7");

        TrademarkFacade trademarkFacade = Mockito.mock(TrademarkFacade.class);

        Mockito.when(trademarkFacade.getTradeMark(any()))
                .thenReturn(trademark);

        Trademark result = trademarkFacade.getTradeMark("TEST");
        assertEquals(result.getWordMarkSpecification(), trademark.getWordMarkSpecification());
    }

    private void createTestTradeMark(String name) {
        repository.save(new Trademark(name, new Date(), new Date(), new Date(), name));
    }
}
