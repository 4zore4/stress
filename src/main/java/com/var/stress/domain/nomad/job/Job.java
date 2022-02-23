package com.var.stress.domain.nomad.job;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.var.stress.domain.nomad.dto.JobDTO;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class Job {

  @JSONField(name ="Job")
  private JobDTO job;

}
