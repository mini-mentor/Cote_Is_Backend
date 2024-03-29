package org.coteis.controller.category;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureRestDocs
@AutoConfigureMockMvc
class PreviousetestApiControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("findAllPrevious() : 카테고리 - 기출 문제 목록 조회")
    void findAllPrevious() throws Exception {
        mockMvc.perform(
                        get("/api/previoustests")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        document("previoustests-get",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        fieldWithPath("[].previoustestNo").description("카테고리 기출 문제 번호 pk"),
                                        fieldWithPath("[].previoustestName").description("카테고리 기출 문제 이름")
                                )
                        )
                )
        ;
    }

    @Test
    @DisplayName("findPrevioustest() : 카테고리 - 특정 기출 문제 조회")
    void findPrevioustest() throws Exception {
        mockMvc.perform(
                        get("/api/previoustests/{id}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo( // rest docs 문서 작성 시작
                        document("previoustest-get", // 문서 조각 디렉토리 명
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                pathParameters( // path 파라미터 정보 입력
                                        parameterWithName("id").description("카테고리 기출 문제 번호 pk")
                                ),
                                responseFields( // response 필드 정보 입력
                                        fieldWithPath("previoustestNo").description("카테고리 기출 문제 번호 pk"),
                                        fieldWithPath("previoustestName").description("카테고리 기출 문제 이름")
                                )
                        )
                )
        ;
    }
}