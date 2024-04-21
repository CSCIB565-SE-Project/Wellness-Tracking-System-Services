package com.dashboard.user;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentService implements IAppointmentService {

    private final AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointmentById(Integer appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    public List<Appointment> getAppointmentsByUserId(String userId) {
        return appointmentRepository.findByTrainerId(userId);
    }    

    public List<Appointment> getAppointmentsByTrainerId(String trainerId) {
        return appointmentRepository.findByTrainerId(trainerId);
    }

    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Integer appointmentId, Appointment updatedAppointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
        if (appointmentOptional.isPresent()) {
            Appointment appointment = appointmentOptional.get();

            appointment.setDate(updatedAppointment.getDate());
            appointment.setTrainerId(updatedAppointment.getTrainerId());
            appointment.setUserId(updatedAppointment.getUserId());

            return appointmentRepository.save(appointment);
        } else {
            throw new RuntimeException("Appointment not found with id: " + appointmentId);
        }
    }

    public void deleteAppointment(ObjectId appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }
}
