#version 330
uniform float uniform_Random;
in vec4    tmpColor;
out vec4   outColor;
void main (void)
{
  outColor = tmpColor + uniform_Random;
}