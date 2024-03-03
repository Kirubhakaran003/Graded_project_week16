package Spring_Boot_Project.Ticket_Tracker_Application.Model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer>{

}
