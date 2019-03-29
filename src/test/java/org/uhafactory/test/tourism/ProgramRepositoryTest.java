package org.uhafactory.test.tourism;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@Transactional
class ProgramRepositoryTest {
    @Autowired
    private ProgramRepository repository;

    @Test
    void testSaveAndGet() {
        // 번호,프로그램명,테마별 분류,서비스 지역,프로그램 소개,프로그램 상세 소개
        // 1,자연과 문화를 함께 즐기는 설악산 기행,"문화생태체험,자연생태체험,",강원도 속초,"설악산 탐방안내소, 신흥사, 권금성, 비룡폭포"," 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다."
        String[] input1 = new String[]{"1,자연과 문화를 함께 즐기는 설악산 기행,\"문화생태체험,자연생태체험,\",강원도 속초,\"설악산 탐방안내소, 신흥사, 권금성, 비룡폭포\",\" 설악산은 왜 설악산이고, 신흥사는 왜 신흥사일까요? 설악산에 대해 정확히 알고, 배우고, 느낄 수 있는 당일형 생태관광입니다.\""};

        Program program = new Program();
        program.setName("자연과 문화를 함께 즐기는 설악산 기행");
        program.setTheme(Lists.newArrayList("문화생태체험,자연생태체험,"));
//        program.setRegion("강원도 속초");

        repository.save(program);

        Program result = repository.getOne(1L);

        assertThat(result.getName()).isEqualTo(program.getName());
    }
}