package com.example.demo.Controller;

import com.example.demo.Model.*;
import com.example.demo.Service.*;

import com.example.demo.dto.FileResponseDTO;
import com.example.demo.dto.ProjectDTO;

import com.example.demo.dto.UserDTO;
import com.example.demo.dto.ViewProjceDTO;
import com.example.demo.repo.ClientRepo;
import com.example.demo.repo.CurrencyRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

@RestController
@RequestMapping(value ="api/v1/user")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class ProjectController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private FileController fileController;

    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private CurrencyRepo currencyRepo;
    @Autowired
    private FreelancerService freelancerService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepo clientRepo;

    @PostMapping("/postProjectFile")
    public String uploadFile(@AuthenticationPrincipal UserDetails userDetails, @RequestParam("file") MultipartFile file,@RequestParam("image") MultipartFile image, @RequestParam("projectDTO") String projectJson) throws Exception {

        UserDTO user = userService.findUserID(userDetails);

        System.out.println("userID"+user.getUserID());
        ResponseEntity<FileResponseDTO>  imageDetails= fileController.uploadFile(image);
       ObjectMapper objectMapper = new ObjectMapper();
       ProjectDTO projectDTO = objectMapper.readValue(projectJson, ProjectDTO.class);


        Client client = clientService.findUserByID(userDetails).getClient();
        projectDTO.setFk_userID(client);
        System.out.println("Herre is the projexc");
        System.out.println(projectDTO);
        System.out.println(projectDTO.getProjectType());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String filePath = "D:/ServerForMyProject/" + file.getOriginalFilename();

        //Saving the image
        String imageName = StringUtils.cleanPath(image.getOriginalFilename());
        String imagePath = imageDetails.getBody().getFileDownloadUri();


        projectService.saveProjectDetails(projectDTO,fileName,filePath,imageName,imagePath);

        File convertFile = new File(filePath);

        convertFile.createNewFile();
        try(FileOutputStream fout = new FileOutputStream(convertFile))
        {
            fout.write(file.getBytes());
        }
        catch(Exception exe)
        {
            exe.printStackTrace();
        }



        return "file has uploaded successfully";
    }

    @GetMapping("/getAllProjectDetails")
    public Map<String, Object> getAllprojectDetails(@AuthenticationPrincipal UserDetails userDetails){
        UserDTO user = userService.findUserID(userDetails);
        Optional<Freelancer> freelancer = freelancerService.getAllDetals(user.getUserID());
        Optional<Language> language = languageService.getLanguagesByID(user.getUserID());
        String city = addressService.getCityByID(user.getUserID());
        List<Project> projects = projectService.getAllProjectDetails();
        Map<String,Object> response = new HashMap<>();
      //  response.put("Projects",projects);
        response.put("FreelancerDetails",freelancer);
        response.put("Languages",language);
        response.put("UserName",user.getUserNames());
        response.put("City",city);
        return response;

    }

   @GetMapping("/downloadFile/{projectID}")
    public ResponseEntity<Object>  downloadFile(@PathVariable String projectID) throws IOException {
            System.out.println(projectID);
            Optional<Project> project = projectService.getProjectsByID(Long.parseLong(projectID));
            System.out.println(project.get().getFileUplod().getFilePath());

            File file = new File(project.get().getFileUplod().getFilePath());
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            //  ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(file.toPath()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition",
                    String.format("attachment; filename=\"%s\"", file.getName()));
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");

            ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                    .contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/txt")).body(resource);


           return responseEntity;
        }
        //  System.out.println(responseEntities);




  ///  @GetMapping("/downloadFile")
    public List<byte[]> downloadFiles() throws IOException {
        List<Project> projects = projectService.getAllProjectDetails();
        List<byte[]> fileContentsList = new ArrayList<>();
        for (Project p : projects) {
            String filename = p.getFileUplod().getFilePath();
            File file = new File(filename);
            byte[] fileContents = Files.readAllBytes(file.toPath());
            fileContentsList.add(fileContents);
        }
        return fileContentsList;
    }

    @GetMapping("/getfie")
    public ResponseEntity<Object> downloadFileONe() throws IOException
    {
        List<Project> projects = projectService.getAllProjectDetails();
        String filename = projects.get(0).getFileUplod().getFilePath();
        File file = new File(filename);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition",
                String.format("attachment; filename=\"%s\"", file.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/txt")).body(resource);

        return responseEntity;
    }

    @GetMapping("/viewallProjectDetails/{projectID}")
    public Map<String, Object> getallprojectDetails(@PathVariable String projectID){
        Optional<Project> project = projectService.getProjectsByID(Long.parseLong(projectID));
        List<Subcategory> subcategories = subCategoryService.findProjectSubCategories(Long.parseLong(projectID));
        System.out.print(project);
        System.out.print(subcategories);

        Map<String, Object> response = new HashMap<>();
        response.put("project", project);
        response.put("subcategories", subcategories);

        return response;

    }
    @GetMapping("/getAllProjectDetails/{passedValue}")
    public Map<String, Object> getiingCurencyDetails(@PathVariable String passedValue){
        System.out.println("hi");
        List<Subcategory> subcategory = subCategoryService.findProjectSubCategories(Long.parseLong(passedValue));
        List<CurrencyType> currencyTypes = currencyService.getAllCurencyTYpes();

        Map<String, Object> response = new HashMap<>();
        response.put("subcateogry", subcategory);
        response.put("currencyTypes", currencyTypes);
        return  response;
    }

    @GetMapping("/getAllProjectDetailsANDClienDetails")
    public Map<String, Object> getAllprojectDetailsWithClient(@AuthenticationPrincipal UserDetails userDetails){

        UserDTO user = userService.findUserID(userDetails);

       Optional<Client> client = clientService.getAllDetals(user.getUserID());
        System.out.println("halloits project details1");
        Optional<Language> language = languageService.getLanguagesByID(user.getUserID());
        String city = addressService.getCityByID(user.getUserID());
        List<Project> projects = projectService.getAllProjectDetails();
       // Optional<Freelancer> freelancer = freelancerService.getAllDetals(user.getUserID());

        System.out.println("halloits project details2");
        Map<String,Object> response = new HashMap<>();
        response.put("Projects",projects);

         response.put("ClientDetails",client);
        response.put("Languages",language);
        response.put("UserName",user.getUserNames());
        response.put("City",city);
        System.out.println("halloits project details3");
      //  response.put("Freelncer",freelancer);
        System.out.println("rsponse"+response);
        return response;

    }

    @GetMapping("/CientPostedProjects")
    public Map<String, Object> getAllClientPostedProjects(@AuthenticationPrincipal UserDetails userDetails){

        UserDTO user = userService.findUserID(userDetails);

        Optional<Client> client = clientService.getAllDetals(user.getUserID());
        System.out.println("halloits project details1");
        Optional<Language> language = languageService.getLanguagesByID(user.getUserID());
        String city = addressService.getCityByID(user.getUserID());
        List<Project> projects = projectService.getAllProjectDetails();
        // Optional<Freelancer> freelancer = freelancerService.getAllDetals(user.getUserID());


        Map<String,Object> response = new HashMap<>();
        List<Project> projectsarray = new ArrayList<>();

        for(Project p : projects){

         if(user.getUserID() == p.getFk_userID().getClientID()){
            projectsarray.add(p);

         }

        }
        response.put("Projects",projectsarray);
        System.out.println("halloits project details2");


        response.put("ClientDetails",client);
        response.put("Languages",language);
        response.put("UserName",user.getUserNames());
        response.put("City",city);
        System.out.println("halloits project details3");
        //  response.put("Freelncer",freelancer);
        System.out.println("rsponse"+response);
        return response;

    }

}


