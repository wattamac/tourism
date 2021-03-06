package org.uhafactory.tour.configuration;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uhafactory.tour.api.dto.DataFile;
import org.uhafactory.tour.api.ProgramCsvReader;
import org.uhafactory.tour.program.Program;
import org.uhafactory.tour.program.ProgramService;
import org.uhafactory.tour.program.region.Region;
import org.uhafactory.tour.program.region.RegionRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.uhafactory.tour.program.RegionNameUtil.removePostfix;


@Profile("dev")
@Transactional
@Service
public class DataInitializer {
    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private ProgramCsvReader programCsvReader;

    @Autowired
    private ProgramService programService;

    @PostConstruct
    public void init() throws IOException {
        regionRepository.saveAll(regions());
        createProgramFromCsv();
    }

    private List<Region> regions() {
        List<Region> regions = Lists.newArrayList();
        regions.addAll(create("서울특별시", "경기도", "강원도"));
        regions.addAll(create("경상남도", "경상북도", "충청북도", "전라북도", "전라남도"));
        regions.addAll(create("강원도 평창군", "강원도 속초시", "강원도 고성군", "강원도 원주시"));
        regions.addAll(create("경기도 의정부시", "경기도 양주시"));
        regions.addAll(create("경상남도 남해군", "경상남도 거제시", "경상남도 산청군", "경상남도 하동군", "경상남도 통영시"));
        regions.addAll(create("경상남도 함양군"));
        regions.addAll(create("경상북도 경주시", "경상북도 영주시", "경상북도 청송군"));
        regions.addAll(create("서울특별시 도봉구", "서울특별시 은평구"));
        regions.addAll(create("전라남도 구례군", "전라남도 도초도", "전라남도 신안군", "전라남도 영암군", "전라남도 완도군"));
        regions.addAll(create("전라남도 여수시", "전라남도 장성군", "전라남도 진도군", "전라남도 화순군"));
        regions.addAll(create("전라북도 남원시", "전라북도 무주군", "전라북도 부안군", "전라북도 정읍시"));
        regions.addAll(create("충청남도 공주시", "충청남도 태안군"));
        regions.addAll(create("충청북도 단양군", "충청북도 보은군", "충청북도 충주시", "충청북도 월악산", "충청북도 소백산"));
        regions.addAll(create("충청북도 제천시"));
        return regions;

    }

    private List<Region> create(String ... names) {
        return Arrays.stream(names)
                .map(n -> Region.create(n, extractKeyword(n)))
                .collect(Collectors.toList());
    }

    private void createProgramFromCsv() throws IOException {
        DataFile dataFile = DataFile.create("classpath:data/insert.csv");
        List<Program> programs = programCsvReader.read(dataFile);

        programService.create(programs);
    }

    static List<String> extractKeyword(String name) {
        String split[] = name.split(" ");

        if(split.length == 1) {
            return Lists.newArrayList(name, removePostfix(name));
        }

        List<String> result = Lists.newArrayList();
        List<String> level1 = Lists.newArrayList(split[0], removePostfix(split[0]));
        List<String> level2 = Lists.newArrayList(split[1], removePostfix(split[1]));

        for(String first : level1) {
            for(String second : level2) {
                result.add(first + " " + second);
            }
        }
        result.addAll(level2);
        return result;
    }
}
