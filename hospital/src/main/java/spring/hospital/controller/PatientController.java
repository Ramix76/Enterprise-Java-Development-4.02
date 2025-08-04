package spring.hospital.controller;

import org.springframework.web.bind.annotation.*;
import spring.hospital.model.Patient;
import spring.hospital.service.PatientService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService service) {
        this.patientService = service;
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAll();
    }

    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.getById(id);
    }

    @GetMapping("/dob")
    public List<Patient> getPatientsByDateRange(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return patientService.getByDateOfBirthRange(startDate, endDate);
    }

    @GetMapping("/admittedByDepartment/{department}")
    public List<Patient> getPatientsByAdmittingDoctorDepartment(@PathVariable String department) {
        return patientService.getByAdmittingDoctorDepartment(department);
    }

    @GetMapping("/admittedByDoctorOff")
    public List<Patient> getPatientsWithDoctorStatusOff() {
        return patientService.getByAdmittingDoctorStatusOff();
    }
}
