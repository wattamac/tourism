package org.uhafactory.tour.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.uhafactory.tour.program.Program;
import org.uhafactory.tour.program.region.Region;

@Mapper
public interface DtoMapper {
    DtoMapper MAPPER = Mappers.getMapper(DtoMapper.class);
    @Mapping(source = "code", target = "region")
    RegionDto toDto(Region region);

    ProgramDto toDto(Program program);

    @Mapping(target = "regions", ignore = true)
    Program toEntity(ProgramDto programDto);

    @Mapping(source = "code", target = "region")
    SimpleRegionDto toSimpleDto(Region region);

    SimpleRegionDto.SimpleProgramDto toSimpleTo(Program program);
}
