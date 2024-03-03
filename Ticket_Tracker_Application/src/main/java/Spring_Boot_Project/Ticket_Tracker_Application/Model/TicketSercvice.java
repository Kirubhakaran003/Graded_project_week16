package Spring_Boot_Project.Ticket_Tracker_Application.Model;

import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;



@Service
public class TicketSercvice {
	@Autowired
	TicketRepository repo;

	public void add(Ticket ticket) {
		repo.save(ticket);
	}
	
	public void update(Ticket ticket) {
		repo.save(ticket);
	}

	public List<Ticket> getAll() {
		return repo.findAll();
	}

	public void delete(Ticket ticket) {
		repo.delete(ticket);
    }
	
	public void bulkAdd(List<Ticket> ticket) {
		repo.saveAll(ticket);
	}
	
	public Ticket findById(int id) {

		if (repo.findById(id).isEmpty())
		{
			return null;
		} else {
			return repo.findById(id).get();
		}
	}
	
	public Ticket getById(int id) {
		Optional<Ticket> tkOptional = repo.findById(id);
		Ticket temp = null;
		if(tkOptional.get() != null) {
			temp = tkOptional.get();			
		}
		return temp;
	}
	
	//Filter using Ticket Title
	public List<Ticket> filterByticketTitle(String columnName, String searchKey) {

		// 1.Dummy
		Ticket dummy = new Ticket();
		dummy.setTitle(searchKey);

		// 2. where with ExampleMatcher
		ExampleMatcher em = ExampleMatcher.matching()
				.withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
				.withIgnorePaths("id","description", "date");

		// 3.Combining Dummy with Where
		Example<Ticket> example = Example.of(dummy, em);

		return repo.findAll(example);
	}
	
}