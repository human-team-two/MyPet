package com.example.team_pro_ex.Controller.mypetboard;

import com.example.team_pro_ex.Service.mypetboard.foodandcafe.FoodCafeService;
import com.example.team_pro_ex.Entity.mypetboard.common.FoodCafeImage;
import com.example.team_pro_ex.Entity.mypetboard.foodandcafe.FoodCafe;
import com.example.team_pro_ex.Service.mypetboard.foodandcafe.FoodCafeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(path = "/mypetboard/foodCafe")
public class FoodCafeController {
    private final FoodCafeService foodcafeService;

    @Autowired
    protected FoodCafeController(FoodCafeService foodcafeService) {

        this.foodcafeService = foodcafeService;
    }

    @GetMapping("/insertFoodCafe")
    public String insertFoodCafeView(FoodCafe foodcafe, Model model) {
        model.addAttribute("foodcafe", foodcafe);
        return "mypetboard/foodCafe/insertFoodCafe";
    }

    @PostMapping("insertFoodCafe")
    public String insertFoodCafe(FoodCafe foodcafe, @RequestParam(value ="foodcafeimage",  required = false) MultipartFile[] foodcafeimage) {
        try {
            foodcafe.setCreateDate(new Date());
            foodcafe.setBookingDate(new Date());
            Long foodcafeSeq = foodcafeService.insertFoodCafe(foodcafe);
            List<FoodCafeImage> imageList = new ArrayList<>();

            for (MultipartFile file : foodcafeimage) {
                if (!file.isEmpty()) {
                    FoodCafeImage entity = new FoodCafeImage(null, UUID.randomUUID().toString(), file.getContentType(), file.getName(), file.getOriginalFilename(), foodcafeSeq);
                    foodcafeService.insertFoodCafeImage(entity);
                    imageList.add(entity);
                    File newFileName = new File(entity.getUuid() + "_" + entity.getOriginalFilename());
                    file.transferTo(newFileName);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //hotelService.insertHotel(hotel);
        return "redirect:/mypetboard/foodCafe/getFoodCafeList";
    }

    @GetMapping ("/getFoodCafeList")
    public String getFoodCafeList(FoodCafe foodcafe , Model model, @PageableDefault(size = 10 , sort = "seq", direction = Sort.Direction.ASC ) Pageable pageable) {
        /*한 화면에 size 글 10개 , sort = "seq" 정렬(시퀀스 번호로 정렬 하겠다) , direction = Sort.Direction.ASC(오름차순으로 정렬)  */

        model.addAttribute("foodcafeList", foodcafeService.getFoodCafeList(pageable));
        model.addAttribute("previous" , pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next" , pageable.next().getPageNumber());


        return "/mypetboard/foodCafe/getFoodCafeList";
    }

    @GetMapping("/getFoodCafe")
    public String getFoodcafe(FoodCafe foodcafe , Model model) {

        model.addAttribute(foodcafeService.getFoodCafe(foodcafe));

        FoodCafeImage foodcafeImage = foodcafeService.getFoodCafeImageEntity(foodcafe.getSeq());
        String path = "/mypetboard/foodCafe/image/" +foodcafeImage.getUuid()+"_"+foodcafeImage.getOriginalFilename();
        model.addAttribute("imageview", path);
        return "mypetboard/foodCafe/getFoodCafe";
    }

    @GetMapping("/image/{imageview}")
    public ResponseEntity<byte[]> imageView3(@PathVariable("imageview")String input_imgName) throws IOException {
        //ResponseEntity<Byte[]>:http 프로토콜을 통해서 byte 데이터를 전달하는 객체 , byte(소문자 = 기본타입)
        //@PathVariable: URL주소의 값을 받아옴
        String path = "C:/work/MyPet/Team_pro_ex/src/main/resources/static/image/" +input_imgName;
        //데이터(이미지)를 전송 하기 위한 객체로써 java에서는 항상 데이터를 스트림 타입으로 전달
        //객체(데이터 저장) :String,int,double
        //String객체는 파일을 컴퓨터가 cpu에서 바로 읽어 들일수 있도록 하는 갳체
        InputStream fis = new FileInputStream(path);
        //Bufferd : cpu에서 데이터를 읽어 올떄 메모리와 캐시 사이에서 Cpu와의 속도 차이를 줄이기 위한 중간 저장 위치
        BufferedInputStream bis =  new BufferedInputStream(fis);
        //byte 배열로 변환
        //HTTP프로토콜은 바이트 단위(배열)로 주고 받음
        byte[] imgByteArr = bis.readAllBytes();
        fis.close();
        //HTTP 프로토콜은 바이트 배열로 데이터를 주고 받기 때문에 Stream이나 버퍼를 통해 변환
        return new ResponseEntity<byte[]>(imgByteArr , HttpStatus.OK);

    }
}
