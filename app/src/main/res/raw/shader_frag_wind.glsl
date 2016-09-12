precision mediump float;
varying vec4 vv4_Colors;
uniform float uf_alpha;
uniform float uf_time;
uniform vec2 uv2_resolution;
highp float rand(vec2 co){
    highp float a = 12.9898;
    highp float b = 78.233;
    highp float c = 43758.5453;
    highp float dt= dot(co.xy ,vec2(a,b));
    highp float sn= mod(dt,3.14);
    return fract(sin(sn) * c);
}
void main()
{

    float alpha = 0.0;

    // inputs
    vec2 resolution = vec2(uv2_resolution.x, uv2_resolution.y);
    const float height = 1.0;
    float y = 0.0;

    // configurações
    const float densityY = 30.0;
    float amplitudeY = 0.06;
    float grossuraY = 80.0;
    float frequenciaCentral = 2.0;

    // preparação das variáveis
    vec2 relativePosition = gl_FragCoord.xy/resolution.xy;
    const int quantityOfWaves = int(floor(height*densityY));

    float preAlpha = 0.0;

    for (int i = 0; i < 10; i++) {

        float fi = float(i);

        // velocidade do vento
        float modi = mod(fi, 6.0);

        // variação da frequencia +/- 1.0, gerado aleatoriamente de acordo com a variável i
        float frequencia = frequenciaCentral + 0.8*cos(rand(vec2(fi, fi))*360.0);

        // resto da divisão entre o tempo e a frequencia
        float resto = mod(uf_time, frequencia);

        // porcentagem do resto no eixo x
        float x = uv2_resolution.x * (resto/frequencia);
        // diferença entre o fragmento atual e o ponto x utilizado como base
        float fragmentRelativePosition =((gl_FragCoord.x-x)/uv2_resolution.x);
        // define o ponto Y relativo ao centro
        float relativeY = uv2_resolution.y * amplitudeY * cos(radians(fragmentRelativePosition*360.0));

        float globalTimeDivFreq = floor(float(uf_time)/frequencia);
        float posAlphaCentralX = rand(vec2(globalTimeDivFreq, fi));
        float sizeAlphaVariationX = 0.4;

        int quantityOfWaves = 10;

        //posicao central y da onda
        float posY = uv2_resolution.y * (y + (fi*(height/float(quantityOfWaves))));

        // define os pontos superiores e inferiores da onda
        float bottomY = posY - relativeY;
        float upY = bottomY + grossuraY;

        if (frequencia > frequenciaCentral && mod(fi, 3.0) == 1.0){
            preAlpha = 0.2;
        } else {
            // define o valor alpha de acordo com a distancia do ponto em relação ao centro da onda
            float leftX = posAlphaCentralX-(sizeAlphaVariationX/2.0);
            float rightX = posAlphaCentralX+(sizeAlphaVariationX/2.0);
            float porcentagemDaFrequencia = resto/frequencia;
            float leftXOnMove = (leftX + (sizeAlphaVariationX * porcentagemDaFrequencia));
            float rightXOnMove = (rightX + (sizeAlphaVariationX * porcentagemDaFrequencia));
            float centerOnMove = leftXOnMove+((rightXOnMove - leftXOnMove)/2.0);

             if (relativePosition.x > leftXOnMove){
                if (relativePosition.x < rightXOnMove){
                    float positionAlpha = 0.0;
                    if (relativePosition.x < centerOnMove){
                        positionAlpha = (relativePosition.x - leftXOnMove)/(centerOnMove - leftXOnMove);
                    } else {
                        positionAlpha = 1.0 - ((relativePosition.x - centerOnMove)/(rightXOnMove - centerOnMove));
                    }
                    if (porcentagemDaFrequencia < 0.5){
                        preAlpha = porcentagemDaFrequencia;
                    } else {
                        preAlpha = 1.0-(porcentagemDaFrequencia);
                    }
                    preAlpha *= positionAlpha;
                }
            }

        }
        if (gl_FragCoord.y > bottomY && gl_FragCoord.y < upY){
            if (gl_FragCoord.y < bottomY + grossuraY/2.0){
                alpha += preAlpha * ((gl_FragCoord.y - (posY - relativeY))/grossuraY);
            } else {
                alpha += preAlpha * (1.0 -((gl_FragCoord.y - (posY - relativeY))/grossuraY));
            }
        }
    }

    float multiply = 0.8;
    gl_FragColor = vec4(1.0, 1.0, 1.0, 1.0);
}
