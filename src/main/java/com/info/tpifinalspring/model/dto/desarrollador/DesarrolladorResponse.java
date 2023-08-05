package com.info.tpifinalspring.model.dto.desarrollador;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class DesarrolladorResponse {
    private List<DesarrolladorDTO> desarrolladores;
    private String errorMessage;
}
