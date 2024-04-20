package com.dashboard.dashboard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.user.Timetable;
import com.dashboard.user.TimetableService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timetables")
@CrossOrigin(origins = "http://localhost:3000")
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

    @GetMapping("/getByUser")
    public ResponseEntity<List<Timetable>> getTimetablesByUserId(@RequestParam Integer userId) {
        List<Timetable> timetables = timetableService.getTimetablesByUserId(userId);
        return ResponseEntity.ok(timetables);
    }

    @PostMapping("/createForUser")
    public ResponseEntity<Timetable> createTimetableForUser(@RequestParam Integer userId, @RequestBody Timetable timetable) {
        Timetable createdTimetable = timetableService.createTimetableForUser(userId, timetable);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTimetable);
    }


    @PutMapping("/update")
    public ResponseEntity<Timetable> updateTimetable(@RequestParam Integer userId, @RequestParam Integer timetableId, @RequestBody Timetable updatedTimetable) {
        // Check if the timetable belongs to the user
        if (!timetableService.doesTimetableBelongToUser(userId, timetableId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Timetable timetable = timetableService.updateTimetable(timetableId, updatedTimetable);
        return ResponseEntity.ok(timetable);
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteTimetable(@RequestParam Integer userId, @RequestParam Integer timetableId) {
        // Check if the timetable belongs to the user
        if (!timetableService.doesTimetableBelongToUser(userId, timetableId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        timetableService.deleteTimetable(timetableId);
        return ResponseEntity.noContent().build();
    }
}
