package com.example.demo.backend.viewModel;

import com.example.demo.backend.domain.PersistentObject;
import lombok.*;

import java.lang.reflect.Type;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LocationViewModel extends PersistentObject {
    String lon;
    String lat;
}