package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService{

    @Autowired
    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Integer id, Appointment updatedAppointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();
            
            appointment.setDate(updatedAppointment.getDate());
            appointment.setProfessionalId(updatedAppointment.getProfessionalId());
            appointment.setUserId(updatedAppointment.getUserId());

            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found with id: " + id);
        }
    }

    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }
}
