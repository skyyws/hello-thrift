namespace java wangs

service Hello {
    string helloString(1:string param)
    i32 helloInt(2:i32 param)
    bool helloBoolean(3:bool param)
    void helloVoid()
    string helloNull()
}