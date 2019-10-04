package com.sys.sysapi.WebController;
import com.sys.sysapi.Base.Result;
import com.sys.sysapi.Utils.UploadUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @PostMapping("/upload")
    public Result upload(@RequestParam("file") MultipartFile file, String filename){
        Result s = new Result(1, "success");
        String usrHome = System.getProperty("user.home");
        try {
            String path = usrHome+"/image/";
            path = path.replace("\\","/");
            System.out.println(path);
            File f = new File(path);
            if(!f.exists()){
                f.mkdirs();
            }
            UploadUtils.uploadFileTest(file,path,filename);
        }catch (Exception e){
            s.setCode(0);
            s.setMessage("失败");
        }
        return s ;
    }
}
