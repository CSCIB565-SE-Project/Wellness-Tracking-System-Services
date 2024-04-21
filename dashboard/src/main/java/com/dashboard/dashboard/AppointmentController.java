package com.dashboard.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.Appointment;
import com.dashboard.user.AppointmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:3000")
public class AppointmentController {

    @Autowired
    private final AppointmentService appointmentService;

    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/byUser")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserId(@RequestParam String userId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId);
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/byTrainer")
    public ResponseEntity<List<Appointment>> getAppointmentsByTrainerId(@RequestParam String trainerId) {
        List<Appointment> appointments = appointmentService.getAppointmentsByTrainerId(trainerId);
        return ResponseEntity.ok(appointments);
    }

    @PostMapping("/createForTrainer")
    public ResponseEntity<Appointment> createAppointmentForTrainer(@RequestParam String trainerId, @RequestBody Appointment appointment) {
        appointment.setTrainerId(trainerId);
        Appointment createdAppointment = appointmentService.createAppointment(appointment);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAppointment);
    }

    @PutMapping("/updateForTrainer")
    public ResponseEntity<Appointment> updateAppointmentForTrainer(@RequestParam Integer appointmentId, @RequestParam String trainerId, @RequestBody Appointment updatedAppointment) {
        updatedAppointment.setTrainerId(trainerId);
        Appointment appointment = appointmentService.updateAppointment(appointmentId, updatedAppointment);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteForTrainer")
    public ResponseEntity<Void> deleteAppointmentForTrainer(@RequestParam Integer appointmentId, @RequestParam String trainerId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }
}
