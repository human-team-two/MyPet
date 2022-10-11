package com.example.team_pro_ex.Controller.mypetboard;

import com.example.team_pro_ex.Entity.member.OwnerMember;
import com.example.team_pro_ex.Entity.mypetboard.beauty.Beauty;
import com.example.team_pro_ex.Entity.mypetboard.common.BeautyImage;
import com.example.team_pro_ex.Service.mypetboard.Beauty.BeautyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping(path = "/mypetboard/beauty")
public class BeautyController {
    private BeautyService beautyService;
    @Autowired
    protected BeautyController(BeautyService beautyService) {

        this.beautyService = beautyService;
    }
    @GetMapping("/insertBeauty")
    public String insertBeautyView(Beauty beauty , Model model , OwnerMember ownerMember) {
        //model.addAttribute("ownerMember", ownerMemberService.getOwnerMember(ownerMember));


        model.addAttribute("beauty" , beauty);
        return "mypetboard/beauty/insertBeauty";
    }
    //사업자회원 테이블에서 OwnerMemberseq, Categoryseq 값을 호텔테이블로 가져와야함
    //로컬 저장소에 저장 (DB에 값 저장)
    @PostMapping("/insertBeauty")
    public String insertBeauty(Beauty beauty,@RequestParam(value ="beautyimage" , required = false) MultipartFile[] beautyimage) {

        try{
            beauty.setCreateDate(new Date());
            beauty.setBookingDate(new Date());
            //게시글 정보 저장
            Long beautySeq = beautyService.insertBeauty(beauty);


            //파일의 메타데이터 저장
            for(MultipartFile file : beautyimage) {
                if(!file.isEmpty()){

                    BeautyImage entity1 = new BeautyImage(null,
                            UUID.randomUUID().toString(),
                            file.getContentType(),
                            file.getName(),
                            file.getOriginalFilename(),
                            beautySeq);//어떤 엔티티에 이미지가 연결이 되어 있나

                    beautyService.insertBeautyImage(entity1);


                    File newFileName = new File(entity1.getUuid()+"_"+ entity1.getOriginalFilename());
                    //서버에 이미지 파일 업로드(저장)
                    file.transferTo(newFileName);

                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/mypetboard/beauty/getBeautyList";
    }

    //이미지를 전송
    @GetMapping(value = "/image/{imgname}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> imageLoading1(@PathVariable("imgname")String input_imgName) throws IOException {
        //ResponseEntity<Byte[]>:http 프로토콜을 통해서 byte 데이터를 전달하는 객체 , byte(소문자 = 기본타입)
        //@PathVariable: URL주소의 값을 받아옴
        String path = "C:/work/MyPet/Team_pro_ex/src/main/resources/static/image/"+input_imgName;
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



    @GetMapping ("/getBeautyList")
    public String getBeautyList(Beauty beauty , Model model, @PageableDefault(size = 10 , sort = "Seq", direction = Sort.Direction.ASC ) Pageable pageable) {
        /*한 화면에 size 글 10개 , sort = "seq" 정렬(시퀀스 번호로 정렬 하겠다) , direction = Sort.Direction.ASC(오름차순으로 정렬)  */

        model.addAttribute("beautyList", beautyService.getBeautyList(pageable));
        model.addAttribute("previous" , pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next" , pageable.next().getPageNumber());


        return "/mypetboard/beauty/getBeautyList";
    }
    @GetMapping("/getBeauty")
    public String getBeauty(Beauty beauty , Model model) {


        model.addAttribute(beautyService.getBeauty(beauty));
        BeautyImage beautyImage = beautyService.getbeautyImageEntity(beauty.getSeq());
        String path = "/mypetboard/beauty/image/"+beautyImage.getUuid()+"_"+beautyImage.getOriginalFilename();
        model.addAttribute("imgLoding", path);


//        List<HotelImage> hotelImageList = (List<HotelImage>) hotelService.getHotelImageEntity(hotel.getSeq());
//        List<String> path1 = new ArrayList<>();
//
//        for(HotelImage hl : hotelImageList){
//            String savePath = "/image/"+hl.getUuid()+"_"+hl.getOriginalFilename();
//            path1.add(savePath);
//        }
//        model.addAttribute("imgLoding1", path1);
        //이미지 불러오기


        return "mypetboard/beauty/getbeauty";
    }

}
