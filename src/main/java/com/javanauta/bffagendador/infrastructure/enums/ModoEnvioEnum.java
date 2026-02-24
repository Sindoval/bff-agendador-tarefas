package com.javanauta.bffagendador.infrastructure.enums;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public enum ModoEnvioEnum {
  EMAIL,
  SMS,
  PUSH,
  WHATSAPP;
}
