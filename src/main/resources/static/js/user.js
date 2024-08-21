let index = {
    init: function (){
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-login").on("click",()=>{
            this.login();
        });
    },

    save: function(){
        //alert('user의 save 함수 호출됨');
        let data = {
                username: $("#username").val(),
                password: $("#password").val(),
                email: $("#email").val(),
        };

       // console.log(data);

        //ajax 호출 시 default가 비동기 호출
        $.ajax({
            type: "post",
            url: "/api/user",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", // 데이터 어떤 타입이야?
            dataType: "json" //요청해서 서버가 응답이 올 때 기본적으로 string 문자열인데, 생긴 게 json이라면 javascrpt오브젝트로 변경해줌
        }).done(function (resp){
            alert("회원가입이 완료되었습니다.");
            //console.log(resp);
             location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));

        }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청
    },

    login: function(){
        //alert('user의 save 함수 호출됨');
        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };

        //ajax 호출 시 default가 비동기 호출
        $.ajax({
            type: "post",
            url: "/api/user/login",
            data: JSON.stringify(data), //http body 데이터
            contentType: "application/json; charset=utf-8", // 데이터 어떤 타입이야?
            dataType: "json" //요청해서 서버가 응답이 올 때 기본적으로 string 문자열인데, 생긴 게 json이라면 javascrpt오브젝트로 변경해줌
        }).done(function (resp){
            alert("로그인이 완료되었습니다.");
            location.href = "/";
        }).fail(function (error){
            alert(JSON.stringify(error));

        }); //ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여  insert 요청
    }
}

index.init(); //index 객체의 init(초기화) 함수 호출