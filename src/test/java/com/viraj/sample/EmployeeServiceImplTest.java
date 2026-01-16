package com.viraj.sample;

import com.viraj.sample.entity.Employee;
import com.viraj.sample.repository.EmployeeRepository;
import com.viraj.sample.service.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private Employee employee; // 👈 MOCK, no objeto real

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void saveEmployee_shouldReturnEmployee() {
        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        when(employee.getEmployeeName()).thenReturn("Test");

        Employee result = employeeService.saveEmployee(employee);

        assertNotNull(result);
        assertEquals("Test", result.getEmployeeName());
        verify(employeeRepository).save(employee);
    }

    @Test
    public void updateEmployee_shouldReturnEmployee() {
        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employee);

        Employee result = employeeService.updateEmployee(employee);

        assertNotNull(result);
        verify(employeeRepository).save(employee);
    }

    @Test
    public void getAllEmployees_shouldReturnList() {
        when(employeeRepository.findAll())
                .thenReturn(Arrays.asList(employee));

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository).findAll();
    }

    @Test
    public void getEmployee_shouldReturnEmployee() {
        when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.of(employee));

        Employee result = employeeService.getEmployee(1L);

        assertNotNull(result);
        verify(employeeRepository).findById(1L);
    }

    @Test
    public void deleteEmployee_shouldCallRepository() {
        doNothing().when(employeeRepository).deleteById(anyLong());

        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }
}


