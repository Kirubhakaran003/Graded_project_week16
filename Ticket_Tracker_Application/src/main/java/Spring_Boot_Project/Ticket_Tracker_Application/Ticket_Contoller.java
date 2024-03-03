package Spring_Boot_Project.Ticket_Tracker_Application;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

import Spring_Boot_Project.Ticket_Tracker_Application.Model.Ticket;
import Spring_Boot_Project.Ticket_Tracker_Application.Model.TicketSercvice;

@Controller
public class Ticket_Contoller {
	@Autowired
	TicketSercvice ticketService;
	
	@RequestMapping("/")
	public String welcome(Model data) {
		List<Ticket> t1=ticketService.getAll();
		data.addAttribute("tickets",t1 );
		return "Welcome";
	}
	
	@PostMapping("/Welcome1")
	public String welcome1(Model data) {
		List<Ticket> t1=ticketService.getAll();
		data.addAttribute("tickets",t1 );
		return "Welcome";
	}
	
	@RequestMapping("/createTicket")
	public String addHome() {
		return "Create_Ticket";
	}
	
	@PostMapping("/saveTicket")
	public String addTicket(@RequestParam String title,@RequestParam String description,@RequestParam String content,@RequestParam LocalDate date,Model data) {
		Ticket t11=new Ticket(0,title, description,content, date);
		ticketService.add(t11);
		
		//ticketService.insert(TicketTrackerApplication.bulider().title(title).description(description).content(content).date(date).bulid());
		
		List<Ticket> t1=ticketService.getAll();
		data.addAttribute("tickets",t1 );
		return "Welcome";
	}
	
	@GetMapping("Edit_Ticket")
	public String update(@RequestParam int id,Model data) {
		
		Ticket ticket=ticketService.getById(id);
		
		if(ticket != null) {
			data.addAttribute("tickets",ticket);
			return "Edit_Ticket";
		}
		else {
			List<Ticket> emp = ticketService.getAll();
			data.addAttribute("emp", emp);
			return "Welcome";
		}
	
	}
	
	@PostMapping("Ticketupdate")
	public String updateTicket(@RequestParam int id,@RequestParam String title,@RequestParam String description,@RequestParam String content,@RequestParam LocalDate date,Model data) {
		Ticket t11=new Ticket(id, title, description,content, date);
		ticketService.update(t11);
		
		List<Ticket> t1 = ticketService.getAll();
		data.addAttribute("tickets",t1);
		return "Welcome";
		
	}
	
	
	
	@GetMapping("/delete_ticket")
	public String delete(@RequestParam int id,Model data) {
		Ticket t11=new Ticket(id, "", "","",null);
		ticketService.delete(t11);
		
		List<Ticket> t1=ticketService.getAll();
		data.addAttribute("tickets",t1 );
		return "Welcome";
	}
	
	@GetMapping("/View_Ticket")
	public String view(@RequestParam int id,Model data) {
		Ticket t11=ticketService.getById(id);
				if(t11 != null) {
					data.addAttribute("tickets", t11);
					return "View_Ticket";
				}
		else {
		List<Ticket> t1=ticketService.getAll();
		data.addAttribute("tickets",t1 );
		return "Welcome";
		}
	}
	
	
	@GetMapping("/searchByticketTitle")
	public String searchByTicketTitle(@RequestParam String search,Model data) {
		List<Ticket> t1=ticketService.filterByticketTitle("title", search);
		data.addAttribute("tickets",t1 );
		return "Welcome";
	}
	
	
	
}
