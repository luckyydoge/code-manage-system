package edu.csu.codemanagesystem.domain.teacher.model.valobj;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuckFormatVO {
    private Double threshold;
    private List<DuplicateCheckReq> objects;
}
