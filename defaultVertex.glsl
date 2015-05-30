#version 330
 uniform mat4 uniform_Projection;
 uniform mat4 uniform_Transform[2];


in vec4  VertexPosition;
in vec4  VertexColor;
 in vec2 VertexUV;

 out vec4 tmpColor;
 out vec2 UV;
   void main(void)
   {
     tmpColor = VertexColor ;
     gl_Position = uniform_Transform[gl_InstanceID] * VertexPosition;
     UV = VertexUV;
     //gl_TexCoord[0] = gl_MultiTexCoord0;
     //glTexCoord is an openGL defined varying array of vec4. Different elements in the array can be used for multi-texturing with
     //different textures, each requiring their own coordinates.
     //gl_MultiTexCoord0 is an openGl defined attribute vec4 containing the texture coordinates for unit 0 (I'll explain units soon) that
     //you give with calls to glTexCoord2f, glTexCoordPointer etc. gl_MultiTexCoord1 contains unit 1, gl_MultiTexCoord2  unit 2 etc.
   }