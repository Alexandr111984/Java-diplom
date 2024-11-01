//package sobinda.javadiplomcloud.util;
//
//import lombok.SneakyThrows;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import sobinda.javadiplomcloud.entity.CloudFileEntity;
//import sobinda.javadiplomcloud.entity.UserEntity;
//import sobinda.javadiplomcloud.model.Role;
//
//import java.io.File;
//import java.time.Instant;
//import java.util.Arrays;
//import java.util.Set;
//import java.util.UUID;
//
//class CloudManagerTest {
//    CloudManager cloudManager;
//    UserEntity userEntity;
//    CloudFileEntity cloudFileEntity;
//    UUID uuid = UUID.fromString("aaa7e24d-bb2d-4885-900e-10771cdb7491");
//    private final byte[] text = "Hello".getBytes();
//
//    @SneakyThrows
//    @BeforeEach
//    void setUp() {
//        cloudManager = new CloudManager();
//        userEntity = UserEntity.builder()
//                .login("111@yandex.ru")
//                .password("1111")
//                .roles(Set.of(Role.ROLE_READ, Role.ROLE_ADMIN))
//                .build();
//
//        cloudFileEntity = CloudFileEntity.builder()
//                .fileName("testFile.txt")
//                .date(Instant.now())
//                .size(11L)
//                .key(uuid)
//                .userEntity(userEntity)
//                .build();
//
//        cloudManager.upload(text, cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
//    }
//
//    @AfterEach
//    void tearDown() {
//        cloudManager.delete(cloudFileEntity);
//        cloudManager = null;
//        userEntity = null;
//        cloudFileEntity = null;
//    }
//
//    @SneakyThrows
//    @Test
//    void uploadTest() {
//        File file = new File("src/main/resources/static/users/" + cloudFileEntity.getKey().toString() + "/" + cloudFileEntity.getFileName());
//        System.out.println("Проверка пути к файлу:  " + file);
//        boolean result = file.exists();
//        Assertions.assertTrue(result);
//    }
//
//    @SneakyThrows
//    @Test
//    void deleteTest() {
//        var result = cloudManager.delete(cloudFileEntity);
//        Assertions.assertTrue(result);
//        System.out.println("Файл получилось удалить: " + result);
//        cloudManager.upload(text, cloudFileEntity.getKey().toString(), cloudFileEntity.getFileName());
//    }
//
//    @Test
//    void getFileTest() {
//        var result = cloudManager.getFile(cloudFileEntity);
//        Assertions.assertEquals(Arrays.toString(text), Arrays.toString(result));
//    }
//
//    @SneakyThrows
//    @Test
//    public void renameFileTo() {
//        String testName = "renameTest";
//        var result = cloudManager.renameFileTo(cloudFileEntity, testName);
//        Assertions.assertTrue(result);
//        cloudFileEntity.setFileName(testName);
//    }
//}
