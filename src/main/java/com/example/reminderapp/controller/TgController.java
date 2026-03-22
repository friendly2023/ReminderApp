package com.example.reminderapp.controller;

import com.example.reminderapp.dto.TelegramMessageRequestDTO;
import com.example.reminderapp.service.SimpleAnalyzer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tg")
@Slf4j
@RequiredArgsConstructor
@Validated
public class TgController {
    private final SimpleAnalyzer simpleAnalyzer;

    @PostMapping(value = "/recording")
    public ResponseEntity<?> createReminder(@RequestBody List<TelegramMessageRequestDTO> telegramMessageRequestDTO) {

        LocalDateTime start = LocalDateTime.now();
        log.info("Времени начала работы: {}", start);

        String word = "Я";
        String responseMessage = String.format(
                "Запрос на анализ. Сообщений:  %s, слово: ' %s'",
                telegramMessageRequestDTO.size(), word
        );

        log.info(responseMessage);

        List<Map<String, Object>> result = simpleAnalyzer.getSimpleStats(telegramMessageRequestDTO, word);

        LocalDateTime end = LocalDateTime.now();
        log.info("Времени конца работы: {}", end);

        Duration duration = Duration.between(start, end);
        log.info("Затраченное время: {}", duration);

        return ResponseEntity.ok(result);
    }

//    @PostMapping(value = "/8")
//    public ResponseEntity<?> createReminder8() {
//
////        List<UserQ> users = List.of(
////                new UserQ(
////                        1L, "Alex", 25, "SPB",
////                        List.of("USER"),
////                        List.of(
////                                new Order(101, 1200, "PAID", "ELECTRONICS"),
////                                new Order(102, 300, "CANCELLED", "BOOKS")
////                        )
////                ),
////                new UserQ(
////                        2L, "Maria", 17, "SPB",
////                        List.of("USER"),
////                        List.of(
////                                new Order(103, 700, "PAID", "FOOD")
////                        )
////                ),
////                new UserQ(
////                        3L, "John", 34, "Moscow",
////                        List.of("USER", "ADMIN"),
////                        List.of(
////                                new Order(104, 1500, "PAID", "ELECTRONICS"),
////                                new Order(105, 500, "PAID", "BOOKS"),
////                                new Order(106, 200, "NEW", "FOOD")
////                        )
////                ),
////                new UserQ(
////                        4L, "Kate", 25, "SPB",
////                        List.of("MANAGER"),
////                        List.of()
////                ),
////                new UserQ(
////                        5L, "Bob", 42, "Moscow",
////                        List.of("USER"),
////                        List.of(
////                                new Order(107, 2500, "PAID", "SPORT")
////                        )
////                ),
////                new UserQ(
////                        6L, "Anna", 31, "Kazan",
////                        List.of("USER"),
////                        List.of(
////                                new Order(108, 900, "NEW", "FOOD"),
////                                new Order(109, 400, "PAID", "FOOD")
////                        )
////                ),
////                new UserQ(
////                        7L, "Mike", 29, "SPB",
////                        List.of("USER", "MANAGER"),
////                        List.of(
////                                new Order(110, 800, "CANCELLED", "ELECTRONICS")
////                        )
////                ),
////                new UserQ(
////                        8L, "Helen", 38, "Moscow",
////                        List.of("ADMIN"),
////                        List.of(
////                                new Order(111, 3000, "PAID", "ELECTRONICS"),
////                                new Order(112, 1200, "PAID", "SPORT")
////                        )
////                )
////        );
//
//        //если ли админ
////        boolean hasAnyAdmin = users.stream()
////                .anyMatch(user -> user.getRoles().contains("ADMIN"));
//
//        //Получить уникальный список всех ролей
////        List<String> uniqueRoles = users.stream()
////                .flatMap(user -> user.getRoles().stream()) // "разворачиваем" списки ролей
////                .distinct() // оставляем только уникальные
////                .toList(); // собираем в список
//
//        //Получить список всех заказов со статусом PAID
////        List<Order> orders = users.stream()
////                .flatMap(o -> o.getOrders().stream())
////                .filter(order -> order.getStatus().equals("PAID"))
////                .toList();
//
//        //Посчитать суммарную стоимость всех заказов
////        double sum = users.stream()
////                .flatMap(userQ -> userQ.getOrders().stream())
////                .mapToDouble(Order::getPrice)              // преобразуем в double
////                .sum();
//
//        //Получить Map <id пользователя, имя>
////        Map<Long,String> user = users.stream()
////                .collect(Collectors.toMap(UserQ::getId, UserQ::getName));
//
//        //Сгруппировать пользователей по возрасту Map<Integer, List<User>>
////        Map<Integer, List<UserQ>> map = users.stream()
////                .collect(Collectors.groupingBy(UserQ::getAge));
//
//        //Сгруппировать заказы по статусу Map<String, List<Order>>
////        Map<String, List<Order>> map = users.stream()
////                .flatMap(user -> user.getOrders().stream())
////                .collect(Collectors.groupingBy(Order::getStatus));
//
//        //Получить среднюю стоимость заказа
////        double srSum = users.stream()
////                .flatMap(user -> user.getOrders().stream())
////                .mapToDouble(Order::getPrice)
////                .average()
////                .orElse(0.0);
//
//        //Найти пользователя с самым дорогим заказом
////        Optional<UserQ> userQ = users.stream()
////                .max(Comparator.comparingDouble(user ->
////                        user.getOrders().stream()
////                                .mapToDouble(Order::getPrice)
////                                .max()
////                                .orElse(0.0)
////                ));
//
//        //1. Получить список имён всех пользователей
////        List<String> result = users.stream()
////                .map(userQ -> userQ.getName())
////                .toList();
//
////        2. Получить пользователей младше 18 лет
////        List<UserQ> result = users.stream()
////                .filter(userQ -> userQ.getAge()>18)
////                .toList();
//
//        //3. Посчитать количество пользователей из города SPB
////        Long result = users.stream()
////                .filter(userQ -> userQ.getCity().equals("SPB"))
////                .count();
//
//        //4. Проверить, есть ли пользователь с ролью ADMIN
////        boolean result = users.stream()
////                .anyMatch(user -> user.getRoles().contains("ADMIN"));
//
////        5. Получить уникальный список всех городов
////        List<String> result = users.stream()
////                .map(userQ -> userQ.getCity())
////                .distinct()
////                .toList();
//
//        //6. Получить список всех заказов со статусом PAID
////            List<Order> result = users.stream()
////                    .flatMap(userQ -> userQ.getOrders().stream())
////                    .filter(order -> order.getStatus().equals("PAID"))
////                    .collect(Collectors.toList());
//
////        7. Посчитать суммарную стоимость всех заказов
////        Double result = users.stream()
////                .flatMap(userQ -> userQ.getOrders().stream())
////                .mapToDouble(Order::getPrice)
////                .sum();
//
//        //8/ Получить Map <userId, city>
////        Map<Long,String> result = users.stream()
////                .collect(Collectors.toMap(UserQ::getId, UserQ::getCity));
//
//        //9. Сгруппировать пользователей по городу Map<String, List<User>>
////        Map<String, List<UserQ>> result = users.stream()
////                .collect(Collectors.groupingBy(UserQ::getCity, Collectors.toList()));
//
//        //10. Сгруппировать заказы по категории Map<String, List<Order>>
////        Map<String, List<Order>> result = users.stream()
////                .flatMap(userQ -> userQ.getOrders().stream())
////                .collect(Collectors.groupingBy(Order::getCategory, Collectors.mapping(order -> order, Collectors.toList())));
////
////        Map<String, List<Order>> result1 = users.stream()
////                .flatMap(userQ -> userQ.getOrders().stream())
////                .collect(Collectors.groupingBy(
////                        Order::getCategory  // ключ - категория
////                        // по умолчанию собирает в List
////                ));
//        return ResponseEntity.ok(result);
//    }
}
