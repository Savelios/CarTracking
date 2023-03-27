package com.example.demo.backend.viewModel;


import com.example.demo.backend.domain.PersistentObject;
import lombok.*;

import java.lang.reflect.Type;

@Data
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageViewModel extends PersistentObject {
    Type typeOfSms;
    String header;
    String body;
}
