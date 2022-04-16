package vacuumlabs.trademark.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vacuumlabs.trademark.model.Trademark;
import vacuumlabs.trademark.service.TrademarkRepository;

import java.util.List;

@Service
public class TrademarkFacade {

    public TrademarkRepository trademarkRepository;

    public Trademark getTradeMark(String name) {
        return trademarkRepository.findByWordMarkSpecification(name);
    }

    public List<Trademark> getTradeMarks(String name) {
        Long name;
        Strin
        return trademarkRepository.findByWordMarkSpecificationContainingIgnoreCase(name);
    }

    public TrademarkRepository getTrademarkRepository() {
        return trademarkRepository;
    }

    @Autowired
    public void setTrademarkRepository(TrademarkRepository trademarkRepository) {
        this.trademarkRepository = trademarkRepository;
    }
}
