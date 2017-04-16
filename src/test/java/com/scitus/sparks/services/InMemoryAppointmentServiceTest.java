package com.scitus.sparks.services;

import com.scitus.sparks.AppointmentBuilder;
import com.scitus.sparks.core.TimeProvider;
import com.scitus.sparks.model.Appointment;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryAppointmentServiceTest {
    private AppointmentService<Integer> appointmentService;

    @Mock
    private TimeProvider timeProvider;

    @Before
    public void setup() {
        appointmentService = new InMemoryAppointmentService(timeProvider);

        appointmentService.create(new AppointmentBuilder().setTitle("title1").setDescription("description1").build());
        appointmentService.create(new AppointmentBuilder().setTitle("title2").setDescription("description2").build());
        appointmentService.create(new AppointmentBuilder().setTitle("title3").setDescription("description3").build());
        appointmentService.create(new AppointmentBuilder().setTitle("title4").setDescription("description4").build());
    }

    @Test
    public void getAll_whenServiceHasFourItems_shouldReturnFullList() throws Exception {
        Collection<Appointment> appointments = appointmentService.list();

        assertThat(appointments.size(), is(4));
    }

    @Test
    public void get_whenGettingItemTwo_shouldReturnInstance() throws Exception {
        Appointment appointment = appointmentService.get(2);

        assertThat(appointment, notNullValue());
        assertThat(appointment.getTitle(), is(equalTo("title2")));
        assertThat(appointment.getDescription(), is(equalTo("description2")));
    }

    @Test
    public void create_addingNewItem_shouldReturnNewItemWithUpdatedId() throws Exception {
        Appointment appointment = appointmentService.create(new AppointmentBuilder().setTitle("title4").setDescription("description4").build());

        assertThat(appointment, notNullValue());
        assertThat(appointment.getId(), is(5));
    }
}