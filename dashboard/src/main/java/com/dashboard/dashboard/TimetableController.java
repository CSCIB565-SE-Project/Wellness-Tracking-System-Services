package com.dashboard.dashboard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.Timetable;
import com.dashboard.user.TimetableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timetables")
public class TimetableController {

    @Autowired
    private final TimetableService timetableService;

    @GetMapping
    public ResponseEntity<List<Timetable>> getAllTimetables() {
        List<Timetable> timetables = timetableService.getAllTimetables();
        return ResponseEntity.ok(timetables);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Timetable> getTimetableById(@PathVariable Integer id) {
        Optional<Timetable> timetable = timetableService.getTimetableById(id);
        return timetable.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("get/user/{userId}")
    public ResponseEntity<List<Timetable>> getTimetablesByUserId(@PathVariable Integer userId) {
        List<Timetable> timetables = timetableService.getTimetablesByUserId(userId);
        return ResponseEntity.ok(timetables);
    }

    @PostMapping("create/user/{userId}")
    public ResponseEntity<Timetable> createTimetableForUser(@PathVariable Integer userId, @RequestBody Timetable timetable) {
        Timetable createdTimetable = timetableService.createTimetableForUser(userId, timetable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTimetable);
    }


    @PutMapping("update/{userId}/{timetableId}")
    public ResponseEntity<Timetable> updateTimetable(@PathVariable Integer userId, @PathVariable Integer timetableId, @RequestBody Timetable updatedTimetable) {
        // Check if the timetable belongs to the user
        if (!timetableService.doesTimetableBelongToUser(userId, timetableId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Timetable timetable = timetableService.updateTimetable(timetableId, updatedTimetable);
        return ResponseEntity.ok(timetable);
    }
    
    @DeleteMapping("delete/{userId}/{timetableId}")
    public ResponseEntity<Void> deleteTimetable(@PathVariable Integer userId, @PathVariable Integer timetableId) {
        // Check if the timetable belongs to the user
        if (!timetableService.doesTimetableBelongToUser(userId, timetableId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        timetableService.deleteTimetable(timetableId);
        return ResponseEntity.noContent().build();
    }
    
}
