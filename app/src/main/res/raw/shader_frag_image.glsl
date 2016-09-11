precision mediump float;
varying vec2 vv2_texCoord;
varying float vf_alpha;
uniform sampler2D us_texture;
void main() {
   vec4 color = texture2D( us_texture, vv2_texCoord);
   if (color.a < 0.01) discard;
   gl_FragColor = vec4(color.r, color.g, color.b, vf_alpha*color.a);
}