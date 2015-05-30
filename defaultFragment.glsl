#version 330
uniform float     uniform_Random;
in vec2 UV;
in vec4 tmpColor;
uniform sampler2D texture_sampler;
//TIL anything with 'out' in a fragment shader is the color(Layout = 0)
out vec4 outColor;


void main (void)
{
  outColor = texture2D(texture_sampler, UV.st);
  //outColor = vec4(0.5, 1, 1, 1);

   // outColor = tmpColor + uniform_Random;
/*
  if (gl_FragColor.r == 0.0f) {
  		gl_FragColor.r = UV.x;#version 330

                              in vec2 TexCoord0;

                              out vec4 FragColor;

                              uniform sampler2D gSampler;

                              void main()
                              {
                                  FragColor = texture2D(gSampler, TexCoord0.xy);
                              }
  	}
  	if (gl_FragColor.g == 0.0f) {
  		gl_FragColor.g = UV.y;
  	}
  	if (gl_FragColor.b == 0.0f) {
  		gl_FragColor.b = 0.5f;
  		}

  		*/
}