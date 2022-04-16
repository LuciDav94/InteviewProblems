package vacuumlabs.trademark.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import vacuumlabs.trademark.facade.TrademarkFacade;
import vacuumlabs.trademark.model.Trademark;

import java.util.List;

@RestController
public class TrademarkController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TrademarkController.class);

    @Autowired
    private TrademarkFacade trademarkFacade;

    @GetMapping
    public Trademark getTrademark(@RequestParam(value = "name") String name) {
        LOGGER.info("Request trademark for value:{}", name);
        Trademark tradeMark = trademarkFacade.getTradeMark(name);
        if (tradeMark == null) {
            LOGGER.info("No trademark found for value:{}", name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return tradeMark;
    }

    @GetMapping("/nearest")
    public List<Trademark> getTrademarks(@RequestParam(value = "name") String name) {
        LOGGER.info("Request nearest trademarks for value:{}", name);
        List<Trademark> list = trademarkFacade.getTradeMarks(name);
        if (list.isEmpty()) {
            LOGGER.info("No nearest trademark value found for {}", name);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return list;
    }
}
