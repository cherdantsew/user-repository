package com.deliverengine.user.controller;

import com.deliverengine.core.enumeration.AuthoritiesConstants;
import com.deliverengine.core.enumeration.RolesConstants;
import com.deliverengine.user.SpringBootApplicationTest;
import com.deliverengine.user.model.dto.CourierDto;
import com.deliverengine.user.model.entity.User;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CourierInternalControllerTests extends SpringBootApplicationTest {
    public static final String INTERNAL_COURIER_CONTROLLER_URL = "/internal/courier/user-service";

    @Test
    public void shouldReturnAllCouriers() throws Exception {
        //todo продолжить писать тесты на интернал контроллеры
        User courier1 = createCourier();
        User courier2 = createCourier();
        authenticateAndReturnToken(RolesConstants.ROLE_ADMIN, List.of(AuthoritiesConstants.VIEW_ALL_COURIERS));
        MvcResult couriersMvcResult = mockmvc.perform(get(INTERNAL_COURIER_CONTROLLER_URL))
            .andExpect(status().isOk())
            .andReturn();
        List<CourierDto> courierDtos = resultAsList(couriersMvcResult, CourierDto.class);
        Assertions.assertEquals(2, courierDtos.size());
    }
}
