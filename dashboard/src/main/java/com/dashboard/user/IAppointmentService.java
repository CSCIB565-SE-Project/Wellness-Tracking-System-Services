package com.dashboard.user;

import java.util.List;
import java.util.Optional;

public interface IAppointmentService {
    List<Appointment> getAllAppointments();
    Optional<Appointment> getAppointmentById(Integer id);
    List<Appointment> getAppointmentsByUserId(String userId);
    List<Appointment> getAppointmentsByTrainerId(String trainerId);
    Appointment createAppointment(Appointment appointment);
    Appointment updateAppointment(Integer id, Appointment updatedAppointment);
    void deleteAppointment(Integer id);
}
