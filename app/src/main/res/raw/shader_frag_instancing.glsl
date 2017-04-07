#version 300 es
precision mediump float;

varying float alpha;
uniform float uf_alpha;
varying vec2 vv2_texCoord;
uniform sampler2D us_texture;



out vec4 fragmentColor;

in vec2 outTexCoord;
in float alpha;

void main() {
   vec4 color = texture2D( us_texture, vv2_texCoord);
   if (color.a < 0.001) discard;
   fragmentColor = vec4(color.r, color.g, color.b, color.a * alpha * uf_alpha);
}
