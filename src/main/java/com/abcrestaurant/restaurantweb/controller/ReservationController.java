package com.abcrestaurant.restaurantweb.controller;

import com.abcrestaurant.restaurantweb.model.Branch;
import com.abcrestaurant.restaurantweb.model.Reservation;
import com.abcrestaurant.restaurantweb.model.User;
import com.abcrestaurant.restaurantweb.service.BranchService;
import com.abcrestaurant.restaurantweb.service.EmailService;
import com.abcrestaurant.restaurantweb.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private BranchService branchService;

    @GetMapping("/reservation")
    public String showReservationPage(Model model) {
        model.addAttribute("branches", branchService.getAllBranches());
        return "user/reservation"; // Points to your Thymeleaf template for the reservation page
    }

    @PostMapping("/reservation")
    public String bookTable(@RequestParam("branchId") Long branchId,
                            @RequestParam("reservationDate") String date,
                            @RequestParam("reservationTime") String time,
                            @RequestParam("numberOfPeople") int numberOfPeople,
                            @RequestParam("username") String username,
                            @RequestParam("email") String email,
                            @RequestParam("phone") String phone,
                            HttpSession session,
                            Model model) {

        try {
            Branch branch = branchService.getBranchById(branchId);
            if (branch == null) {
                model.addAttribute("error", "Selected branch does not exist.");
                model.addAttribute("branches", branchService.getAllBranches());
                return "user/reservation"; // Return to the form page with an error message
            }

            LocalDate reservationDate = LocalDate.parse(date);
            LocalTime reservationTime = LocalTime.parse(time);

            if (reservationService.checkTableAvailability(branch, reservationDate, reservationTime, numberOfPeople)) {
                // Retrieve the logged-in user from the session
                User loggedInUser = (User) session.getAttribute("loggedInUser");

                if (loggedInUser == null) {
                    model.addAttribute("error", "You must be logged in to make a reservation.");
                    return "redirect:/"; // Redirect to login page if the user is not logged in
                }

                // Create and set the reservation details
                Reservation reservation = new Reservation();
                reservation.setBranch(branch);
                reservation.setUsername(username);
                reservation.setEmail(email);
                reservation.setPhone(phone);
                reservation.setReservationDate(reservationDate);
                reservation.setReservationTime(reservationTime);
                reservation.setNumberOfPeople(numberOfPeople);

                // Set the logged-in user as a reference in the reservation
                reservation.setUser(loggedInUser);

                reservationService.bookTable(reservation);
                model.addAttribute("success", "Your reservation is confirmed! Please check your email for details.");

                return "user/reservation"; // Ensure this points to the correct success page
            } else {
                model.addAttribute("error", "Sorry, the table is fully booked for the selected date and time.");
                model.addAttribute("branches", branchService.getAllBranches());
                return "user/reservation"; // Return to the form page with an error message
            }
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Invalid date or time format.");
            model.addAttribute("branches", branchService.getAllBranches());
            return "user/reservation"; // Return to the form page with an error message
        }
    }


    @GetMapping("/reservationlist")
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "staff/reservation/reservationlist"; // The Thymeleaf template to display the table
    }



    @GetMapping("/reservation/confirmation")
    public String showConfirmationPage(){
        return "staff/reservation/confirmation";  // This points to your Thymeleaf template
    }

    @Autowired
    private EmailService emailService;
    @RequestMapping(value = "/reservation/send-email/{id}", method = RequestMethod.GET)
    public String sendEmail(@PathVariable("id") Long reservationId, Model model) {
        Reservation reservation = reservationService.getReservationById(reservationId);

        if (reservation != null) {
            // Prepare the email content
            String subject = "Reservation Confirmation";
            String message = "Dear " + reservation.getUsername() + ",\n\n"
                    + "Your reservation with ID " + reservation.getId() + " is confirmed.\n"
                    + "Reservation Date: " + reservation.getReservationDate() + "\n"
                    + "Reservation Time: " + reservation.getReservationTime() + "\n\n"
                    + "Thank you for choosing us.";

            // Send the email
            emailService.sendEmail(reservation.getEmail(), subject, message);

            // Add attributes to the model
            model.addAttribute("reservationId", reservation.getId());
            model.addAttribute("email", reservation.getEmail());

            return "staff/reservation/confirmation";  // Redirect to the confirmation page
        } else {
            // Handle the case where the reservation was not found
            model.addAttribute("error", "Reservation not found.");
            return "error"; // Redirect to an error page or handle appropriately
        }
    }


}
