package org.uhafactory.tour.api.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.uhafactory.tour.program.Program;
import org.uhafactory.tour.program.region.Region;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface DtoMapper {
    DtoMapper MAPPER = getMapper(DtoMapper.class);
    @Mapping(source = "code", target = "region")
    RegionDto toDto(Region region);

    ProgramDto toDto(Program program);

    @Mapping(target = "regions", ignore = true)
    Program toEntity(ProgramDto programDto);

    @Mapping(source = "code", target = "region")
    SimpleRegionDto toSimpleDto(Region region);

    SimpleRegionDto.SimpleProgramDto toSimpleTo(Program program);
}
