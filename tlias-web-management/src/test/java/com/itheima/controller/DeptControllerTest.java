package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.service.DeptService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DeptController.class)
class DeptControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeptService deptService;

    @Test
    void listShouldReturnDepartmentDataAsJson() throws Exception {
        given(deptService.findAll()).willReturn(List.of(
                new Dept(1, "教研部", LocalDateTime.of(2024, 1, 1, 8, 0), LocalDateTime.of(2024, 1, 2, 8, 0))
        ));

        mockMvc.perform(get("/depts").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data[0].id").value(1))
                .andExpect(jsonPath("$.data[0].name").value("教研部"));
    }

    @Test
    void getByIdShouldReturnDepartmentWhenExists() throws Exception {
        given(deptService.getById(2)).willReturn(
                new Dept(2, "研发部", LocalDateTime.of(2024, 1, 1, 8, 0), LocalDateTime.of(2024, 1, 2, 8, 0))
        );

        mockMvc.perform(get("/depts/2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"))
                .andExpect(jsonPath("$.data.id").value(2))
                .andExpect(jsonPath("$.data.name").value("研发部"));
    }

    @Test
    void deleteShouldAcceptQueryParameterId() throws Exception {
        mockMvc.perform(delete("/depts").param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));

        verify(deptService).deleteById(1);
    }

    @Test
    void addShouldAcceptJsonBody() throws Exception {
        mockMvc.perform(post("/depts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"教研部\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(1))
                .andExpect(jsonPath("$.msg").value("success"));
    }

    @Test
    void addShouldReturnErrorWhenNameAlreadyExists() throws Exception {
        doThrow(new DuplicateKeyException("Duplicate")).when(deptService).add(org.mockito.ArgumentMatchers.any(Dept.class));

        mockMvc.perform(post("/depts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"教研部\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0))
                .andExpect(jsonPath("$.msg").value("部门名称已存在"));
    }
}
