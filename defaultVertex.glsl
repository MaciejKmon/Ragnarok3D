#version 330
 uniform mat4 uniform_Projection;
 uniform mat4 uniform_Transform[2];

 in vec4  VertexPosition;
 in vec4  VertexColor;

 out vec4 tmpColor;


   void main(void)
   {
     tmpColor = VertexColor ;
     gl_Position = uniform_Transform[gl_InstanceID] * VertexPosition;
   }