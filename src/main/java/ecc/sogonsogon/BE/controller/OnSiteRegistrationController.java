package ecc.sogonsogon.BE.controller;

import ecc.sogonsogon.BE.exception.UserNotFoundException;
import ecc.sogonsogon.BE.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regiOn")
@CrossOrigin(origins ="*", methods={RequestMethod.GET})
@Validated
public class OnSiteRegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/nickname/{id}")
    public ResponseEntity<String> validationName(@PathVariable String id, @RequestParam String name){
        try {
            boolean isSame = userService.validateName(id, name);

            // Response: 200 OK
            if (isSame) {
                return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 닉네임입니다.");
            } else {
                // Response: 404 에러
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("이미 사용중인 닉네임입니다.");
            }
        } catch (UserNotFoundException e) {
            // Response: 404 에러
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 사용자입니다.");
        } catch (Exception e) {
            // Response: 500 에러
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("서버 오류가 발생했습니다. 다시 시도해주세요.");
        }
    }
    // 예외 처리
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}