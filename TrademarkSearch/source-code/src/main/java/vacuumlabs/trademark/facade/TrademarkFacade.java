package vacuumlabs.trademark.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vacuumlabs.trademark.aop.LogExecutionTime;
import vacuumlabs.trademark.model.Trademark;
import vacuumlabs.trademark.service.TrademarkRepository;

import java.util.List;

@Service
public class TrademarkFacade {

    public TrademarkRepository trademarkRepository;

    @LogExecutionTime
    public Trademark getTradeMark(String name) {
        return trademarkRepository.findByWordMarkSpecification(name);
    }

    @LogExecutionTime
    public List<Trademark> getTradeMarks(String name) {
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
