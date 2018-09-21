package tacos.tacos;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface TacoRepository 
         extends PagingAndSortingRepository<Taco, Long> {

}
