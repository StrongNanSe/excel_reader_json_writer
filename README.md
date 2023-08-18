#  사용방법
엑셀과 JSON의 라이선스 이름(LICENSE_NAME) 기준으로 비교하여 엑셀의 속성들과 JSON 속성과 다를 경우 엑셀 기준의 속성으로 JSON 파일을 새로 생성하는 도구
1. 수정할 json 형식의 license_attribute 파일과
   참조할 xlsx 형식의 license_attribute 파일을 하나씩
   license_attribute_editrt.jar 파일과 같은 디렉토리에 위치 시킵니다.
   
2. CMD에서 license_attribute_editrt.jar 파일이 위치한 디렉토리로 이동후
   "java -jar license_attribute_editrt.jar"
   위 명령어로 jar 파일을 실행시킵니다.
   
3. jar 파일 실행이 끝나면 "modified_license_Attribute.json" 란 이름의 수정된 json 파일이 생성된것을 확인할 수 있습니다.

# Release 
- 2023/08/18

# Backlog
- 엑셀에 라이선스가 추가될 경우 또는 엑셀에만 라이선스가 있는 경우 생성되는 JSON에 라이선스 추가하는 기능 추가