package org.uhafactory.test.api.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.uhafactory.test.tourism.program.Program;
import org.uhafactory.test.tourism.region.Region;

@Mapper
public interface RegionMapper {
    RegionMapper MAPPER = Mappers.getMapper(RegionMapper.class);
    @Mapping(source = "code", target = "region")
    RegionDto toRegionDto(Region region);

    ProgramDto toProgramDto(Program program);
}
